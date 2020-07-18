import java.util.*
import kotlin.Comparator

object Sequence {


    /* Sequence는 Stream과 마찬가지로 지연 개발 (lazy) 컬렉션 연산을 하므로
    중간 결과 값을 저장하지 않으므로 연산의 속도를 빠르게 할 수 있다. */
    //그러나 최종연산을 사용하지 않는다면 결과가 출력되지 않으므로 최종연산을 사용해야 할 경우만 사용해야 함

    // 리스트, 해쉬맵, 큐, 스택

    @JvmStatic
    fun main(args: Array<String>) {

/*


        //sort(), find(), filter(), map(),  groupBy(), max()


        // 리스트

        // 오름차순으로 정렬하기
        val numberList = listOf("d", "c", "b", "a")
        numberList.asSequence()
            .sortedWith(Comparator { t1, t2 ->
                if (t1 > t2) {
                    1
                } else {
                    -1
                }
            })
            .forEach(System.out::println)


        //리스트 선언
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8).asSequence()

        val two = list.find {
            it == 2
        }.let(System.out::println)


        //짝수만 걸러내기
        val evens = list
            .filter {
                it % 2 == 0
            }
            .forEach(System.out::println)

        // 값들을 2배로 만든 리스트
        // sequence는 중간연산은 항상 지연 계산이기 때문에 최종연산이 호출되지 않으면 filter()와 map()이 불리지 않음
        // 그래서 toList() (최종연산) 를 붙여야 늦춰졌던 계산들이 수행된다.
        val doubleList = list
            .filter {
                println("filter($it)");
                it%2 == 0 }
            .map {
                it * it
                println("map($it)");
            }.toList()


        //sequnece 적용 안한 것
        // 모든 원소를 access 함
        println(listOf(1,2,3,4).map { print("access $it ");it*it }.find { print("result $it "); it>3 })

        //sequence 적용
        // 2번째 원소의 find()메소드에서 결과가 나온 후 바로 뒤의 연산을 실행하지 않음
        println(listOf(1,2,3,4)
            .asSequence()
            .map { print("access $it ");it*it }
            .find { print("result $it "); it>3 })


        // list의 값들을 key로 한 Map 생성
        val map = list.groupBy {
            it
        }.forEach(System.out::println)


        //리스트 중 최대값 찾기 1번째
        val max = list.max().let(System.out::println)

        //리스트 중 최대값 찾기 2번째
        val max2 = list.maxBy {
            it
        }.let(System.out::println)

        val max3 = list.maxWith(Comparator { p0, p1 ->
                when {
                p0 > p1 -> 1  // 내꺼가 더 크면 참
                p0 == p1 -> 0
                else -> -1
            }
        }).let(System.out::println)


*/


/*

        // 해쉬맵 선언
        val hashMap = hashMapOf<Int, String>(Pair(3, "a"), Pair(4, "d"), Pair(2, "b"), Pair(1, "a"))

        // key를 기준으로 정렬된 맵
        val sortedByKeyMap: Map<Int, String> = hashMap.toList().sortedBy { it.first }.toMap()

        sortedByKeyMap.forEach { (i, s) ->
            println("key = $i , value = $s")
        }

        // key를 기준으로 정렬된 리스트
        val sortedByKeyList: Array<Int> = hashMap.toList().sortedBy { it.first }.toMap().keys.toTypedArray()
        val sortedByKeyList: IntArray = hashMap.toList().sortedBy { it.first }.toMap().keys.toIntArray()

        // value를 기준으로 정렬된 리스트
        val sortedByValueList: Array<String> = hashMap.toList().sortedBy { it.second }.toMap().values.toTypedArray()

        val filteredPair = sortedByKeyMap.filter {
            it.key == 2
        }.let(System.out::println)

*/




        val queue: Queue<Int> = LinkedList(listOf(1,2,3,4))

        queue.peek().let(System.out::println) // 큐의 맨 앞 원소 단순 참조 (꺼내지 않고)

/*        queue.poll().let(System.out::println)*/ //큐의 맨 앞 원소 반환 (꺼내고)

        queue.offer(5) // 큐에 원소 집어넣기

        queue.remove() // 맨 앞 원소 제거


        queue.asSequence().map {
            println("$it")
            it * 2
        }.toList()

        println(queue)


    }
}